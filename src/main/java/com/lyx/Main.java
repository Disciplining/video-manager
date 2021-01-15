package com.lyx;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.List;

public class Main
{
    private static String sourceDirPath;
    private static String destDirPath;

    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("请输入参数");
            return;
        }
        sourceDirPath = args[0];
        destDirPath = args[1];
        if (!FileUtil.exist(sourceDirPath))
        {
            System.out.println("源目录不存在");
            return;
        }
        if (!FileUtil.exist(destDirPath))
        {
            FileUtil.mkdir(destDirPath);
        }

        List<File> videos = FileUtil.loopFiles(sourceDirPath, Main::isVideo );
        videos.forEach(Main::videoFileDeal);
    }

    public static boolean isVideo(File file)
    {
        String type = FileTypeUtil.getType(file);
        if (StrUtil.equalsIgnoreCase(type, "rmvb")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "flv")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mp4")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mpg")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "wmv")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "avi")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mov")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "asx")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "asf")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "rm")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "3gp")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "m4v")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "dat")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mkv")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "vob")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mpeg")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mpg")) { return true; }
        if (StrUtil.equalsIgnoreCase(type, "mts")) { return true; }

        return false;
    }

    public static void videoFileDeal(File video)
    {
        if (FileUtil.exist(video))
        {
            System.out.println("文件移动" + StrUtil.sub(IdUtil.fastSimpleUUID(), 0, 4));
            String fileName = FileUtil.getName(video);

            if (fileName.length() >= 10)
            {
                File mVideo = FileUtil.rename(video, StrUtil.sub(fileName, 0, 9), true, true);
                FileUtil.move(mVideo, FileUtil.file(destDirPath), true);
                return;
            }

            FileUtil.move(video, FileUtil.file(destDirPath), true);
        }
    }
}