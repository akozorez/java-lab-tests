import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Matrix {

    public enum Error {
        COLUMNS_NOT_EQUAL(1, "Number of columns A, B are not equal"),
        DIFFERENT_SIZE(2, "Matrix size is different"),
        EMPTY_MATRIX(3, "Matrix is empty"),
        NEGATIVE_SIZE(4, "Negative matrix size");

        private final int code;
        private final String description;

        private Error(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String toString() {
            return code + ": " + description;
        }

        public IllegalArgumentException toException() {
            return new IllegalArgumentException(this.toString());
        }
    }

    public static double[][] randomMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw Error.NEGATIVE_SIZE.toException();
        }

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = Math.random() * 10;
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix[0].length != secondMatrix.length) {
            throw Error.COLUMNS_NOT_EQUAL.toException();
        }

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                double cell = 0;
                for (int i = 0; i < secondMatrix.length; i++) {
                    cell += firstMatrix[row][i] * secondMatrix[i][col];
                }
                result[row][col] = cell;
            }
        }
        return result;
    }

    public static double[][] sum(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            throw Error.DIFFERENT_SIZE.toException();
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            if (firstMatrix[i].length != secondMatrix[i].length)
                throw Error.DIFFERENT_SIZE.toException();
        }

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = firstMatrix[row][col] + secondMatrix[row][col];
            }
        }
        return result;
    }

    public static double[][] subtract(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            throw Error.DIFFERENT_SIZE.toException();
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            if (firstMatrix[i].length != secondMatrix[i].length)
                throw Error.DIFFERENT_SIZE.toException();
        }

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = firstMatrix[row][col] - secondMatrix[row][col];
            }
        }
        return result;
    }

    public static double[][] readFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        int m = lines.get(0).split(" ").length;
        int n = lines.size();
        double[][] matrix = new double[n][m];
        for(int i = 0; i < n; i++){
            String [] splittedLine = lines.get(i).split(" ");
            for (int j = 0; j < splittedLine.length; j++) {
                matrix[i][j] = Double.parseDouble(splittedLine[j]);
            }
        }
        return matrix;
    }
}