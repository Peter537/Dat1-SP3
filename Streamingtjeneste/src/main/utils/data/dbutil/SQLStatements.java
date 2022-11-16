package main.utils.data.dbutil;

public class SQLStatements {
    public static String allCountries() {
        return "SELECT * FROM country";
    }

    public static String setACountry(String code, String name, String continent, String region, int surfaceArea, int population, String localName, String governmentForm, int code2) {
        return "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) VALUES ('" + code + "', '" + name + "', '" + continent + "', '" + region + "', " + surfaceArea + ", " + population + ", '" + localName + "', '" + governmentForm + "', " + code2 + ")";
    }
}
