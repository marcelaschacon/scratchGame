package com.scratch.game.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.scratch.game.model.symbol.Symbol;

public class MatrixSerializer extends JsonSerializer<Symbol[][]> {
    @Override
    public void serialize(Symbol[][] matrix, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray(); // Start the outer array
        for (Symbol[] row : matrix) {
            gen.writeStartArray(); // Start a row
            for (Symbol symbol : row) {
                gen.writeString(symbol.getName()); // Write each symbol's name
            }
            gen.writeEndArray(); // End the row
        }
        gen.writeEndArray(); // End the outer array
    }
}