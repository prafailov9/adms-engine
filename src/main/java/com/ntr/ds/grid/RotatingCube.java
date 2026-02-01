package com.ntr.ds.grid;

import java.util.Arrays;

public class RotatingCube {
    public static void main(String[] args) throws InterruptedException {
        double[][] vertices = {
                {-1, -1, -1}, {-1, -1, 1}, {-1, 1, -1}, {-1, 1, 1},
                {1, -1, -1}, {1, -1, 1}, {1, 1, -1}, {1, 1, 1}
        };

        int[][] edges = {
                {0, 1}, {1, 3}, {3, 2}, {2, 0}, // Back edges
                {4, 5}, {5, 7}, {7, 6}, {6, 4}, // Front edges
                {0, 4}, {1, 5}, {2, 6}, {3, 7}  // Connecting edges
        };

        double angleX = 0;
        double angleY = 0;
        double angleZ = 0;

        while (true) {
            char[][] screen = new char[20][40];
            for (char[] row : screen) {
                Arrays.fill(row, ' ');
            }

            double[][] rotatedVertices = new double[8][3];
            for (int i = 0; i < vertices.length; i++) {
                double[] v = vertices[i];

                // Rotation around X-axis
                double x = v[0];
                double y = v[1] * Math.cos(angleX) - v[2] * Math.sin(angleX);
                double z = v[1] * Math.sin(angleX) + v[2] * Math.cos(angleX);

                // Rotation around Y-axis
                double newX = x * Math.cos(angleY) + z * Math.sin(angleY);
                double newZ = -x * Math.sin(angleY) + z * Math.cos(angleY);
                x = newX;
                z = newZ;

                // Rotation around Z-axis
                double newY = y * Math.cos(angleZ) - x * Math.sin(angleZ);
                newX = y * Math.sin(angleZ) + x * Math.cos(angleZ);

                rotatedVertices[i][0] = newX;
                rotatedVertices[i][1] = newY;
                rotatedVertices[i][2] = newZ;
            }

            double scale = 5;
            int centerX = 20;
            int centerY = 10;

            for (int[] edge : edges) {
                double[] p1 = rotatedVertices[edge[0]];
                double[] p2 = rotatedVertices[edge[1]];

                int x1 = (int) (p1[0] * scale + centerX);
                int y1 = (int) (p1[1] * scale + centerY);
                int x2 = (int) (p2[0] * scale + centerX);
                int y2 = (int) (p2[1] * scale + centerY);

                drawLine(screen, x1, y1, x2, y2);
            }

            // Print the screen
            System.out.print("\033[H\033[2J"); // Clear the console
            for (char[] row : screen) {
                System.out.println(row);
            }

            angleX += 0.05;
            angleY += 0.05;
            angleZ += 0.05;

            Thread.sleep(100);
        }
    }

    private static void drawLine(char[][] screen, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (x1 >= 0 && x1 < screen[0].length && y1 >= 0 && y1 < screen.length) {
                screen[y1][x1] = '#';
            }
            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}
