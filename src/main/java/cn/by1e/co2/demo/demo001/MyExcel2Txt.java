package cn.by1e.co2.demo.demo001;

import cn.by1e.ox.core.constant.Constants;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bangquan.qian
 * @date 2020-08-03 21:51
 */
public class MyExcel2Txt {

    private static final String xls_path = "/Users/chainz/Downloads/";

    private static final String txt_path = "/Users/chainz/Temporary/";

    private static final String[] files = new String[]{
            "2020-9-2.csv",
    };

    public static void main(String[] args) throws Throwable {
        String date = getDate();
        int len = files.length;
        for (int idx = 0; idx < len; idx++) {
            String file = files[idx];
            write(read(genCSV(file)), genTXT(file, date, idx));
        }
    }

    private static String genCSV(String file) {
        return xls_path + file;
    }

    private static String genTXT(String file, String date, int idx) {
        return txt_path + file + "." + date + "." + (idx + 1) + "." + RandomStringUtils.randomAlphanumeric(4) + ".txt";
    }

    private static String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private static void write(List<String> strs, String txt) throws Throwable {
        if (CollectionUtils.isEmpty(strs)) {
            return;
        }
        FileUtils.writeStringToFile(new File(txt), StringUtils.join(strs, ",\n"));
        ConsoleUtils.jsons(txt, strs.size());
    }

    @SuppressWarnings(Constants.UNCHECKED)
    private static List<String> read(String csv) throws Throwable {
        return InvokeUtils.function(
                (List<String>) FileUtils.readLines(new File(csv)),
                list -> CollectionUtils.isEmpty(list) ?
                        Collections.emptyList() :
                        list.stream().map(e -> StringUtils.isBlank(e) ? StringUtils.EMPTY : e.split(",")[0].replaceAll("[^0-9]", StringUtils.EMPTY).trim())
                                .filter(StringUtils::isNotBlank)
                                .filter(e -> e.length() == 6)
                                .collect(Collectors.toList())
        );
    }

}
