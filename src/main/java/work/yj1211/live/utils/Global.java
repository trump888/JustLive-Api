package work.yj1211.live.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import work.yj1211.live.vo.BannerInfo;
import work.yj1211.live.vo.TV;
import work.yj1211.live.vo.UpdateInfo;
import work.yj1211.live.vo.platformArea.AreaInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

//全局变量
@Component
public class Global {
    public static Map<String, List<List<AreaInfo>>> platformAreaMap= new HashMap<>();
    public static Map<String, Map<String, Map<String, AreaInfo>>> AllAreaMap= new HashMap<>(); //<分区类型，<分区名,<平台，分区信息>>>
    public static Map<String, String> DouyuCateMap = new HashMap<>();
    public static Map<String, String> BilibiliCateMap = new HashMap<>();
    public static Map<String, String> HuyaCateMap = new HashMap<>();
    public static Map<String, String> CCCateMap = new HashMap<>();
    public static Map<String, String> EgameCateMap = new HashMap<>();
    public static Map<String, String> EgameCateMapVer = new HashMap<>();
    public static List<String> AreaTypeSortList = new ArrayList<>();
    public static Map<String, List<String>> AreaInfoSortMap = new HashMap<>();
    private static String m3uPath;
    private static String updateFilePath;
    private static String bannerInfoFilePath;
    public static List<TV> m3uResult;
    public static UpdateInfo updateInfo;
    public static List<BannerInfo> updateInfoList;


    @Value("${path.m3uPath}")
    public void setM3uPath(String m3uPath) {
        Global.m3uPath = m3uPath;
    }

    @Value("${path.updateFilePath}")
    public void setUpdateFilePath(String updateFilePath) {
        Global.updateFilePath = updateFilePath;
    }

    @Value("${path.bannerInfoFilePath}")
    public void setBannerInfoFilePath(String bannerInfoFilePath) {
        Global.bannerInfoFilePath = bannerInfoFilePath;
    }

    public static String getM3uPath() {
        return m3uPath;
    }

    public static String getUpdateFilePath() {
        return updateFilePath;
    }

    public static String getBannerInfoFilePath() {
        return bannerInfoFilePath;
    }

    public static AreaInfo getAreaInfo(String platform, String areaName){
        List<List<AreaInfo>> platformList = platformAreaMap.get(platform);
        Iterator<List<AreaInfo>> it = platformList.iterator();
        while(it.hasNext()){
            List<AreaInfo> areaInfoList = it.next();
            Iterator<AreaInfo> it2 = areaInfoList.iterator();
            while(it2.hasNext()){
                AreaInfo areaInfo = it2.next();
                if (areaName.equals(areaInfo.getAreaName())){
                    return areaInfo;
                }
            }
        }
        return null;
    };

    public static String readTxtFile(String filePath){
        String readResult = "";
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    readResult = readResult + lineTxt;
                }
                read.close();
            }else{
                readResult = "找不到指定的文件";
            }
        } catch (Exception e) {
            readResult = "读取文件内容出错";
        }

        return readResult;
    }
}
