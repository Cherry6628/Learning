package model;

public enum TransportType {
    ROAD, TRAIN, FLIGHT;

    public static TransportType from(String s) {
        if (s == null) return null;

        s = s.strip().toLowerCase();

        return switch (s) {
            case "r", "road", "bus" -> ROAD;
            case "t", "train", "rail" -> TRAIN;
            case "f", "flight", "air", "plane" -> FLIGHT;
            default -> null;
        };
    }
}
