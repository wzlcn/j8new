package com.wzl.j8new.datastructure.recursion;

/**
 * @author wangzhilong
 * @date 2020/8/6 16:51
 * @Description: 递归---迷宫问题
 */
/*示例图片位置：src/main/resources/static/image/migong.jpg
从左上角到右下角，找出一条路径*/
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组模拟出来迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙，上下两行全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右两行全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //显示地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);
        //显示地图
        System.out.println("小球走过路的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //说明如下：
    //map表示地图 i，j表示出发的位置，本题代表(1，1)
    //如果小球能够到达(6，5)，则代表通路找到
    //约定，当map[i][j] = 0表示该点没有走过，为1表示墙，2表示通路可以走，3表示该点已经走过但是走不通
    //自定义策略(小球走的顺序) 下->右->上->左，该点走不通再回溯
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2){
            return true;
        } else {
            if (map[i][j] == 0){
                map[i][j] = 2;
                if (setWay(map,i + 1,j)){//向下走
                    return true;
                } else if (setWay(map,i,j + 1)){//向右走
                    return true;
                } else if (setWay(map,i - 1,j)){//向上走
                    return true;
                } else if (setWay(map,i,j - 1)){//向左走
                    return true;
                } else {//上下左右都走不通 按约定置为3
                    map[i][j] = 3;
                    return false;
                }
            } else {//为1，2，3 代表走不通或已走过
                return false;
            }
        }
    }
}
