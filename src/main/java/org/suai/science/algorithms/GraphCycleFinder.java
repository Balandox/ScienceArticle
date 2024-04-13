package org.suai.science.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCycleFinder {

    //  Graph modeled as list of edges
/*    static int[][] graph = {
                    {0, 2}, {0,3}, {0,5},
                    {1, 2}, {1,3}, {1, 5}, {1,7}, // по сути матрица из 8 строк и двух столбцов
                    {2, 3}, {2, 6}, {2,7},           // [0,2], то есть graph[0, 1] - 2, graph[0,4] - OutOfBoundException
                    {3, 4},
                    {4, 5}, {4, 6}, {4,7}
    };*/

    static List<int[]> cycles = new ArrayList<int[]>();

    static  int amountOfAdjacencyCheck = 0;
    static int amountOfVisitedCheck = 0;

    static int amountOfCycleCounting = 0;

    public static void launchCycleFinder(int[][] graph) {

        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                findNewCycles(new int[] {graph[i][j]}, graph); // передаем внутрь массив, в который уже положили одну вершину (алгоритм берет в качестве старта все вершины графа)

        int amountFourCycle = 0;
        int amountSixCycle = 0;
        int amountEightCycle = 0;

        amountOfCycleCounting = cycles.size();
        for (int[] cy : cycles) {
            if(cy.length == 4)
                amountFourCycle++;
            else if(cy.length == 6)
                amountSixCycle++;
            else if(cy.length == 8)
                amountEightCycle++;
            String s = "" + cy[0];
            for (int i = 1; i < cy.length; i++)
            {
                s += "," + cy[i];
            }

            o(s);
        }
        System.out.println("Amount of cycles 4-length = " + amountFourCycle);
        System.out.println("Amount of cycles 6-length = " + amountSixCycle);
        System.out.println("amountOfAdjacencyCheck = " + amountOfAdjacencyCheck);
        System.out.println("amountOfVisitedCheck = " + amountOfVisitedCheck);
        System.out.println("amountOfCycleCounting = " + amountOfCycleCounting);
        //System.out.println("Amount of cycles 8-length = " + amountEightCycle);

    }

    static void findNewCycles(int[] path, int[][] graph) {
        int n = path[0]; // текущая вершина в пути (крайняя на данный момент)
        // а в конце массива path содержится наша изначальная вершина пути (на ней должен замыкаться цикл)
        int x;
        int[] sub = new int[path.length + 1]; // расширяем текущий путь на 1 вершину

        // Итерируемся по всем рёбрам графа
        for (int i = 0; i < graph.length; i++) {
            for (int y = 0; y <= 1; y++) {
                amountOfAdjacencyCheck++;
                if (graph[i][y] == n) { // если ребро относится к текущей вершине
                    // получаем смежную вершину, в которую ведет данное ребро
                    x = graph[i][(y + 1) % 2];
                    amountOfVisitedCheck++;

                    if (!visited(x, path)) {
                        // если смежная вершина не встречалась ранее в пути, то делаем ее текущей
                        sub[0] = x;
                        System.arraycopy(path, 0, sub, 1, path.length);
                        //  и углубляемся дальше
                        findNewCycles(sub, graph);
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
