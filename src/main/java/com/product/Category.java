package com.product;

/**
 * Representa una Categoría con un ID, nombre, etiqueta y estado.
 */
public class Category {

    /* El identificador único de la categoría. */
    private Integer category_id;
    /* El nombre de la categoría. */
    private String category;
    /* La etiqueta asociada con la categoría. */
    private String tag;
    /* El estado de la categoría. */
    private Integer status;

    /**
     * Construye una nueva Categoría con el ID, nombre, etiqueta y estado especificados.
     *
     * @param category_id el identificador único de la categoría
     * @param category el nombre de la categoría
     * @param tag la etiqueta asociada con la categoría
     * @param status el estado de la categoría
     */
    public Category(Integer category_id, String category, String tag, Integer status) {
        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    /**
     * Devuelve el identificador único de la categoría.
     *
     * @return el identificador único de la categoría
     */
    public Integer getCategory_id() {
        return category_id;
    }

    /**
     * Establece el identificador único de la categoría.
     *
     * @param category_id el nuevo identificador único de la categoría
     */
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Devuelve el nombre de la categoría.
     *
     * @return el nombre de la categoría
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param category el nuevo nombre de la categoría
     */
    public void setCategory(String category) {
        this.category = category;
    }


    /**
     * Devuelve la etiqueta asociada con la categoría.
     *
     * @return la etiqueta asociada con la categoría
     */
    public String getTag() {
        return tag;
    }

    /**
     * Establece la etiqueta asociada con la categoría.
     *
     * @param tag la nueva etiqueta asociada con la categoría
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Devuelve el estado de la categoría.
     *
     * @return el estado de la categoría
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece el estado de la categoría.
     *
     * @param status el nuevo estado de la categoría
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Devuelve una representación en cadena de la categoría.
     *
     * @return una representación en cadena de la categoría
     */
    @Override
    public String toString() {
        return String.format("{id=%d, category=%s, tag=%s, status=%d}",category_id,category,tag,status);
    }
    
    /**
    * Compara este objeto con el objeto especificado para determinar si son iguales.
    * 
    * @param o el objeto a comparar con este objeto
    * @return {@code true} si los objetos son iguales; {@code false} en caso contrario
    */ 
    @Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (o == null || getClass() != o.getClass()) 
            return false;

        Category category1 = (Category) o;
        
        return category_id.equals(category1.category_id) && category.equals(category1.category) && tag.equals(category1.tag);
    }
}