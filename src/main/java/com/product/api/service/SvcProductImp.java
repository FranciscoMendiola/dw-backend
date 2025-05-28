package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.in.DtoStockIn;
import com.product.api.dto.in.DtoValidateProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductImage;
import com.product.common.dto.ApiResponse;
import com.product.common.mapper.MapperProduct;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class SvcProductImp implements SvcProduct {

	@Autowired
	private RepoProduct repoProduct;

	@Autowired
	private RepoProductImage repoProductImage;

	@Autowired
	private MapperProduct mapper;

	@Value("${app.upload.dir}")
	private String uploadDir;

	@Override
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		try {
			List<Product> products = repoProduct.findAll();
			return new ResponseEntity<>(mapper.fromProductList(products), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<List<DtoProductListOut>> getActiveProducts() {
		try {
			List<Product> products = repoProduct.getActiveProducts();
			return new ResponseEntity<>(mapper.fromProductList(products), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<List<DtoProductListOut>> getProductsByCategory(Integer categoryId) {
		try {
			List<Product> products = repoProduct.getProductsByCategory(categoryId);
			return new ResponseEntity<>(mapper.fromProductList(products), HttpStatus.OK);
		} catch (DataAccessException e) {
			String msg = e.getLocalizedMessage();
			if (msg != null) {
				if (msg.contains("fk_product_category"))
					throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");
			}
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<DtoProductOut> getProduct(Integer productId) {
		try {
			DtoProductOut product = validateProductId(productId);
			List<String> images = readProductImagesFile(productId);
			product.setImages(images);

			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> createProduct(DtoProductIn in) {
		try {
			Product product = mapper.fromDto(in);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido registrado"), HttpStatus.CREATED);
		} catch (DataAccessException e) {
			String msg = e.getLocalizedMessage();
			if (msg != null) {
				if (msg.contains("ux_product_gtin"))
					throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
				if (msg.contains("ux_product_product"))
					throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
				if (msg.contains("fk_product_category"))
					throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");
			}
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateProduct(Integer productId, DtoProductIn in) {
		try {
			validateProductId(productId);
			Product product = mapper.fromDto(productId, in);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido actualizado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			String msg = e.getLocalizedMessage();
			if (msg != null) {
				if (msg.contains("ux_product_gtin"))
					throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
				if (msg.contains("ux_product_product"))
					throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
				if (msg.contains("fk_product_category"))
					throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");
			}
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateProductStock(Integer productId, DtoStockIn in) {
		try {
			Product product = validateSimpleProductId(productId);

			product.setStock(product.getStock() + in.getQuantity());
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El stock del producto ha sido actualizado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableProduct(Integer productId) {
		try {
			validateProductId(productId);
			Product product = repoProduct.findById(productId).get();
			product.setStatus(1);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido activado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> disableProduct(Integer productId) {
		try {
			validateProductId(productId);
			Product product = repoProduct.findById(productId).get();
			product.setStatus(0);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido desactivado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	private DtoProductOut validateProductId(Integer productId) {
		DtoProductOut product = repoProduct.getProduct(productId);
		try {
			if (product == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			}
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
		return product;
	}

	private Product validateSimpleProductId(Integer productId) {
		Product product = repoProduct.findById(productId).orElse(null);
		try {
			if (product == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			}
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
		return product;
	}

	private List<String> readProductImagesFile(Integer productId) {
		try {
			List<ProductImage> productImages = repoProductImage.findByProductId(productId);
			if (productImages.isEmpty())
				return new ArrayList<>();

			// Crear una lista para almacenar las imágenes codificadas
			List<String> encodedImages = new ArrayList<>();

			for (ProductImage productImage : productImages) {
				String imageUrl = productImage.getImage();

				// Si la URL comienza con "/", la eliminamos para obtener la ruta relativa
				if (imageUrl.startsWith("/")) {
					imageUrl = imageUrl.substring(1);
				}

				// Construir el Path
				Path imagePath = Paths.get(uploadDir, imageUrl);

				// Verifica que el archivo exista
				if (!Files.exists(imagePath))
					continue;

				try {
					// Leer los bytes de la imagen y codificarlos a Base64
					byte[] imageBytes = Files.readAllBytes(imagePath);
					String encodeImage = Base64.getEncoder().encodeToString(imageBytes);
					encodedImages.add(encodeImage);
				} catch (IOException e) {
					throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
							"Error al leer el archivo: " + imagePath.toString());
				}
			}
			return encodedImages;
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<Integer> validateProduct(DtoValidateProductIn in) {
		try {
			Product product = repoProduct.findByProduct(in.getProduct());
			if(product == null)
				throw new ApiException(HttpStatus.BAD_REQUEST, "El producto con nombre: " + in.getProduct() + " no esta registrado.");

			if(product.getStatus() == 0)
				throw new ApiException(HttpStatus.FORBIDDEN, "El producto con nombre: " + in.getProduct() + " no esta disponible.");

			if(!product.getGtin().equals(in.getGtin()))
				throw new ApiException(HttpStatus.FORBIDDEN, "El producto con nombre: " + in.getProduct() + " y el gtin proporcionado no coincide con el registrado en base de datos.");

			if(Math.abs(product.getPrice() - in.getPrice()) > 0)
				throw new ApiException(HttpStatus.FORBIDDEN, "El producto con nombre: " + in.getProduct() + " y el precio unitario proporcionado no coincide con el registrado en base de datos.");

			if(product.getStock()-in.getQuantity() < 0)
				throw new ApiException(HttpStatus.FORBIDDEN, "El producto con nombre: " + in.getProduct() + " cuenta con un stock de " + product.getStock() +".");

			return new ResponseEntity<>(product.getProductId(), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
}
