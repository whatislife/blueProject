package com.zhht.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 
 * @author ydd
 * @date 2018/12/28 10:04
 * @version 1.5
 * @description 加载c++的dll文件
 *
 */
public interface Similarity extends Library {

	//本地环境
//	Similarity INSTANCE = (Similarity) Native.loadLibrary(ProjectPath.getRootPath(PropertiesUtil.getProperty("cop.properties", "DLL_PATH")), Similarity.class);
	//linux环境
	Similarity INSTANCE = (Similarity) Native.loadLibrary("CalSimilarity",	Similarity.class);

	/**
	 * @author ydd
	 * @date 2018/12/28 10:05
	 * @version 1.5
	 * @description dll文件中的接口
	 * 
	 * @param pstrFeatureA
	 *            特征值A
	 * @param pstrFeatureB
	 *            特征值B
	 * @return 相似度 0-1
	 */
	float CalSimilarity(String pstrFeatureA, String pstrFeatureB);
}
