-- Seleccionar la base de datos
USE dwb;

-- Crear tablas en el orden correcto
SOURCE ddl/3.region.sql;
SOURCE ddl/4.customer.sql;
SOURCE ddl/5.customer_image.sql;
SOURCE ddl/6.category.sql;
SOURCE ddl/7.product.sql;
SOURCE ddl/8.product_image.sql;

-- Si tienes inserciones iniciales, ejecutarlas después
-- SOURCE dml/3.region.sql;
-- SOURCE dml/4.customer.sql;
-- SOURCE dml/5.customer_image.sql;
-- SOURCE dml/6.category.sql;
-- SOURCE dml/7.product.sql;
-- SOURCE dml/8.product_image.sql;

-- Mensaje de confirmación
SELECT 'Base de datos inicializada correctamente' AS mensaje;
