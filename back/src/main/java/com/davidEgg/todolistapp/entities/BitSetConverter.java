package com.davidEgg.todolistapp.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.BitSet;

@Converter(autoApply = true)
public class BitSetConverter implements AttributeConverter<BitSet, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(BitSet attribute) {
        return attribute.toByteArray();
    }

    @Override
    public BitSet convertToEntityAttribute(byte[] dbData) {
        return BitSet.valueOf(dbData);
    }
}