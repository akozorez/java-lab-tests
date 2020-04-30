import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import java.io.IOException;

public class matrixTest
{
    @Test
    public void randomMatrixCreate()
    {
        double[][] firstMatrix = Matrix.randomMatrix(3, 3);
        assertEquals(firstMatrix.length, 3);
        assertEquals(firstMatrix[0].length, 3);
    }

    @Test
    public void randomMatrixThrows1()
    {
        assertThrows(IllegalArgumentException.class, () -> Matrix.randomMatrix(0, 3));
    }

    @Test
    public void randomMatrixThrows2()
    {
        assertThrows(IllegalArgumentException.class, () -> Matrix.randomMatrix(3, -1));
    }

    @Test
    public void matrixSum1()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMatrix = {{2, 4, 6}, {8, 10, 12}, {14, 16, 18}};
        assertArrayEquals(expectedMatrix, Matrix.sum(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixSumThrows1()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        assertThrows(IllegalArgumentException.class, () -> Matrix.sum(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixMultiplyThrows1()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{1, 2, 3}};
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixMultiplyThrows2()
    {
        double[][] firstMatrix = Matrix.randomMatrix(3, 3);
        double[][] secondMatrix = Matrix.randomMatrix(2, 3);
        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixMultiply1()
    {
        double[][] firstMatrix = Matrix.randomMatrix(3, 10);
        double[][] secondMatrix = Matrix.randomMatrix(10, 5);
        Matrix.multiply(firstMatrix, secondMatrix);
    }

    @Test
    public void matrixSubtractThrows1()
    {
        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        assertThrows(IllegalArgumentException.class, () -> Matrix.subtract(firstMatrix, secondMatrix));
    }

    @Test
    public void matrixFromInput() throws IOException {
        double [][] firstMatrix = Matrix.readFromFile("src/resources/input.txt");
        double [][] expMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertArrayEquals(expMatrix, firstMatrix);
    }
}