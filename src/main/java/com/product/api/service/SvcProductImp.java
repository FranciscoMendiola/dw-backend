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
	public ResponseEntity<List<DtoProductListOut>> getProductsByCategory(Integer id) {
		try {
			List<Product> products = repoProduct.getProductsByCategory(id);
			return new ResponseEntity<>(mapper.fromProductList(products), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<DtoProductOut> getProduct(Integer id) {
		try {
			DtoProductOut product = validateProductId(id);
			List<String> images = readProductImagesFile(id);
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
			if (e.getLocalizedMessage().contains("ux_product_gtin"))
				throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
			if (e.getLocalizedMessage().contains("ux_product_product"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
			if (e.getLocalizedMessage().contains("fk_product_category"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateProduct(Integer id, DtoProductIn in) {
		try {
			validateProductId(id);
			Product product = mapper.fromDto(id, in);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido actualizado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_product_gtin"))
				throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
			if (e.getLocalizedMessage().contains("ux_product_product"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
			if (e.getLocalizedMessage().contains("fk_product_category"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateProductStock(Integer id, Integer newStock) {
		try {
			validateProductId(id);
			Product product = repoProduct.findById(id).get();
			if (newStock < 0)
				throw new ApiException(HttpStatus.BAD_REQUEST, "El stock no puede ser negativo");

			product.setStock(newStock);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El stock del producto ha sido actualizado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableProduct(Integer id) {
		try {
			validateProductId(id);
			Product product = repoProduct.findById(id).get();
			product.setStatus(1);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido activado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> disableProduct(Integer id) {
		try {
			validateProductId(id);
			Product product = repoProduct.findById(id).get();
			product.setStatus(0);
			repoProduct.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido desactivado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	private DtoProductOut validateProductId(Integer id) {
		DtoProductOut product = repoProduct.getProduct(id);
		try {
			if (product == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			}
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
		return product;
	}

	private List<String> readProductImagesFile(Integer product_id) {
		try {
			List<ProductImage> productImages = repoProductImage.findByProductId(product_id);
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
}
