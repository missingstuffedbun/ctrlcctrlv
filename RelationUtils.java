package paper;

import java.io.IOException;

/**
 * Created by missi on 2017/4/26.
 */
public class RelationUtils {

    ExcelUtils eu = new ExcelUtils();
    int[][] stationArray = new int[16][16];
    int[][] pipeArray = new int[31][3];

    public RelationUtils() throws IOException {
        // 获取泵站关系
        for (int id = 0; id < 16; id++) {
            String[] row = eu.readRow(2,id);
            for (int i = 0; i < 16; i++) {
                if (i < row.length) {
                    stationArray[id][i] = Integer.valueOf(row[i]);
                } else {
                    stationArray[id][i] = -1;
                }
            }
        }
        // 获取管道关系
        for (int id = 0; id < 31; id++) {
            String[] row = eu.readRow(3,id);
            for (int i = 0; i < 3; i++) {
                pipeArray[id][i] = Integer.valueOf(row[i]);
            }
        }
    }

    /**
     * 通过staiton的id找到其所有上下游关系
     * @param id
     * @return
     */
    public int[] getRelaionById(int id) {
        return stationArray[id];
    }

    /**
     * 用station或者sewer的id去找pipe的id
     * @param itemId
     * @return
     */
    public int findDownPipeByItemId(int itemId) {
        for (int i = 0; i < 31; i++) {
            if (pipeArray[i][1] == itemId) {
                return pipeArray[i][0];
            }
        }
        return -10;
    }
}
