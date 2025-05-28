package com.product.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.NoArgsConstructor;

/**
 * Controlador raíz que redirige a la documentación de Swagger UI.
 */
@NoArgsConstructor
@RestController
@RequestMapping("/")
@Hidden
public class CtrlRedirectDocs {

    /**
     * Redirige las solicitudes a la raíz (/) hacia la documentación de Swagger UI.
     *
     * @return RedirectView que apunta a /swagger-ui/index.html#/
     */
    @GetMapping
    public RedirectView redirectToSwagger() {
        RedirectView redirectView = new RedirectView("/swagger-ui/index.html#/");
        redirectView.setContextRelative(true); // Asegura que la ruta sea relativa al contexto
        return redirectView;
    }
}