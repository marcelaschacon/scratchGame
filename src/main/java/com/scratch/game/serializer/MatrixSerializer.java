package com.scratch.game.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.scratch.game.model.symbol.Symbol;

public class MatrixSerializer extends JsonSerializer<Symbol[][]> {
    @Override
    public void serialize(Symbol[][] matrix, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Symbol[] row : matrix) {
            gen.writeStartArray();
            for (Symbol symbol : row) {
                gen.writeString(symbol.getName());
            }
            gen.writeEndArray();
        }
        gen.writeEndArray();
    }
}
