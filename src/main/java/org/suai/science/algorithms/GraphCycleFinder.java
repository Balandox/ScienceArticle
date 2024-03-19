package org.suai.science.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCycleFinder {

    //  Graph modeled as list of edges
    static int[][] graph = {
                    {0, 2}, {0, 4}, {0, 5}, // по сути матрица из 8 строк и двух столбцов
                    {1, 3},                   // [0,2], то есть graph[0, 1] - 2, graph[0,4] - OutOfBoundException
                    {2, 3}, {2, 4}, {2,5},    // [0,4]
                    {3, 4}
            };

    static List<int[]> cycles = new ArrayList<int[]>();

    public static void main(String[] args) {

        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                findNewCycles(new int[] {graph[i][j]}); // передаем внутрь массив, в который уже положили одну вершину (алгоритм берет в качестве старта все вершины графа)

        for (int[] cy : cycles) {
            String s = "" + cy[0];
            for (int i = 1; i < cy.length; i++)
            {
                s += "," + cy[i];
            }

            o(s);
        }

    }

    static void findNewCycles(int[] path) {
        int n = path[0]; // текущая вершина в пути (крайняя на данный момент)
        // а в конце массива path содержится наша изначальная вершина пути (на ней должен замыкаться цикл)
        int x;
        int[] sub = new int[path.length + 1]; // расширяем текущий путь на 1 вершину

        // Итерируемся по всем рёбрам графа
        for (int i = 0; i < graph.length; i++) {
            for (int y = 0; y <= 1; y++) {
                if (graph[i][y] == n) { // если ребро относится к текущей вершине
                    // получаем смежную вершину, в которую ведет данное ребро
                    x = graph[i][(y + 1) % 2];

                    if (!visited(x, path)) {
                        // если смежная вершина не встречалась ранее в пути, то делаем ее текущей
                        sub[0] = x;
                        System.arraycopy(path, 0, sub, 1, path.length);
                        //  и углубляемся дальше
                        findNewCycles(sub);
                    }
                    // если в пути как минимум 3 вершины и путь привел в начальную вершину
                    else if ((path.length > 2) && (x == path[path.length - 1])) {
                        // нормализуем путь
                        int[] p = normalize(path);
                        // инвертируем путь
                        int[] inv = invert(p);
                        // если ни обычного, ни инвертированного пути не было ранее, то мы нашли новый путь
                        if (isNew(p) && isNew(inv)) {
                            cycles.add(p);
                        }
                    }
                }
            }
        }
    }

    //  check of both arrays have same lengths and contents
    static Boolean equals(int[] a, int[] b)
    {
        Boolean ret = (a[0] == b[0]) && (a.length == b.length);

        for (int i = 1; ret && (i < a.length); i++)
        {
            if (a[i] != b[i])
            {
                ret = false;
            }
        }

        return ret;
    }

    //  create a path array with reversed order
    static int[] invert(int[] path)
    {
        int[] p = new int[path.length];

        for (int i = 0; i < path.length; i++)
        {
            p[i] = path[path.length - 1 - i];
        }

        return normalize(p);
    }

    //  делаем так, чтобы путь начинался от меньшей вершины
    static int[] normalize(int[] path)
    {
        int[] p = new int[path.length];
        int x = Arrays.stream(path).min().getAsInt(); // минимальный в массиве
        int n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x)
        {
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    //  compare path against known cycles
    //  return true, iff path is not a known cycle
    static Boolean isNew(int[] path)
    {
        Boolean ret = true;

        for(int[] p : cycles)
        {
            if (equals(p, path))
            {
                ret = false;
                break;
            }
        }

        return ret;
    }

    static void o(String s)
    {
        System.out.println(s);
    }

    //  check if vertex n is contained in path
    static Boolean visited(int n, int[] path) {
        for (int p : path)
            if (p == n)
                return true;
        return false;
    }

}
