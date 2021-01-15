package com.lyx;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

public class Test
{
    public static void main(String[] args)
    {
        System.out.println("文件移动" + StrUtil.sub(IdUtil.fastSimpleUUID(), 0, 4));
    }
}