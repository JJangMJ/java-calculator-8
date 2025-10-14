package calculator.domain;

public record CustomDelimiter(
        Character delimiter
) {
    public static CustomDelimiter createDelimiter(Character delimiter) {
        return new CustomDelimiter(delimiter);
    }
}
