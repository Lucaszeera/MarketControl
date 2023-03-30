package br.com.fiap.marketcontrol.models;

public record RestValidationError(Integer cod, String field, String message) {
    
}
