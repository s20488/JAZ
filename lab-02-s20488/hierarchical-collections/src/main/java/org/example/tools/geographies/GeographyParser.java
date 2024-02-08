package org.example.tools.geographies;

import org.example.model.Geography;
import org.example.tools.abstractions.IParse;

public class GeographyParser implements IParse<Geography> {
    @Override
    public Geography parse(String line) {
        String[] words = line.trim().split(";"); //usuwa dodatkowe spacje na początku i końcu ciągu znaków i rozdziela ciąg znaków po znaku ";"

        int id = Integer.parseInt(words[0]);
        String type = words[1];
        String name = words[2];
        String code = words[3];
        Integer parentId = words[4].equals("NULL") ? null : Integer.parseInt(words[4]);

        return new Geography(id, name, type, code, parentId);
    }
}
