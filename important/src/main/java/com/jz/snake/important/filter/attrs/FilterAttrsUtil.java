package com.jz.snake.important.filter.attrs;

import com.alibaba.druid.util.StringUtils;
import com.jz.snake.important.filter.attrs.base.AttrData;
import com.jz.snake.important.filter.attrs.base.AttrDataOption;
import com.jz.snake.important.filter.attrs.base.Operation;
import com.jz.snake.important.filter.attrs.structure.EventFilterAttrDataPool;
import com.jz.snake.important.filter.attrs.structure.FilterAttrDataPool;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.util.NutType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @ClassName: FilterUtil 
* @Description:过滤条件初始化参数实体类
* @author xuezhi_xu@hansight.com
* @date 2016年8月25日 下午1:55:11 
*
 */
public class FilterAttrsUtil implements Serializable {
	
	private static Logger logger = LoggerFactory.getLogger(FilterAttrsUtil.class);

	
	private static final long serialVersionUID = 7928557724512697871L;
	
	private static Map<String,Object> filterAttrDatasMap = null;

	private static final String CONFFILENAME = File.separator +"filter"+File.separator +"attrs";

	/**
	 * 获取过滤条件属性列表
	 *
	 * @return
	 */
	public static Map<String, Object> getFilterAttrDatasMap() {

		synchronized (FilterAttrsUtil.class) {

			if(null == filterAttrDatasMap){
				filterAttrDatasMap = initFilterAttrDatasMap();
			}
		}

		return filterAttrDatasMap;
	}

	/**
	 * 获取过滤条件对象
	 * @return
	 */
	public static Object getFilterAttrDatasMap(String filterObjName){

		Object filterObj = null;

		filterAttrDatasMap = getFilterAttrDatasMap();
		if(!StringUtils.isEmpty(filterObjName) && filterAttrDatasMap.containsKey(filterObjName)){
			filterObj = filterAttrDatasMap.get(filterObjName);
		}else{
			logger.error("FilterAttrsUtil error获取过滤条件属性失败，filterObj name:" + filterObjName);
		}
		return filterObj;

	}


	public static void setFilterAttrDatasMap(Map<String, Object> filterAttrDatasMap) {
		FilterAttrsUtil.filterAttrDatasMap = filterAttrDatasMap;
	}
	/**
	 * 获取配置文件路径
	 * @return
	 */
	private static String getPath(){
		return System.getProperty("conf.dir")  + CONFFILENAME;
	}

	/**
	 * 从配置文件中读取过滤查询条件属性
	 * @return
	 */
	private static Map<String, Object> initFilterAttrDatasMap() {


		Map<String, Object> filterMap = new HashMap<String, Object>();

		String filePath = getPath();
		File file = new File(filePath);
		if(file!=null){
            if(file.isDirectory()){
                File[] fileArray= file.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                    	File tempFile = fileArray[i];
                        if(tempFile.isDirectory()){

                        	assemFilterAttrDataMap(tempFile,filterMap);

                        }
                    }
                }
            }
        }
		return filterMap;
	}
	/**
	 * 更新过滤属性到json文件中
	 * @param filterAttrDatasMap
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean updateFilterAttrToFile(Map<String, Object> filterAttrDatasMap){

		boolean flag = false;
		boolean updateFlag = true;
		if(null != filterAttrDatasMap && !filterAttrDatasMap.isEmpty()){
			for (Map.Entry<String, Object> entry : filterAttrDatasMap.entrySet()) {
				String fileName = entry.getKey();
				if(!StringUtils.isEmpty(fileName)){
					switch(fileName){
					case "event" :
						updateFlag = (updateFilterAttrs(fileName,filterAttrDatasMap.get(fileName),true) && updateFlag);
						break;
					default :
						updateFlag = (updateFilterAttrs(fileName,filterAttrDatasMap.get(fileName),false) && updateFlag);
						break;
					}
				}
			}
		}
		return flag && updateFlag;

	}
	/**
	 * 更新过滤条件属性文件
	 * @param object
	 * @param fileName 模块名称
	 * @param
	 * @return
	 */
	public static boolean updateFilterAttrs(String fileName ,Object object, boolean isEvent) {

		boolean flag = true;
		boolean updateFlag = true;

		LinkedHashMap<String ,Map<String,Operation>> operationMap = null;

		LinkedHashMap<String, AttrData> attrDatasMap = null;

		LinkedHashMap<String, List<AttrDataOption>> optionDatasMap = null;

		LinkedHashMap<String,List<AttrDataOption>> eventTypeAttrsMap = null;
		if(isEvent){
			EventFilterAttrDataPool efadPool = (EventFilterAttrDataPool) object;
			if(null != efadPool){
				operationMap = efadPool.getOperationMap();
				attrDatasMap = efadPool.getAttrDatasMap();
				optionDatasMap = efadPool.getOptionDatasMap();
				eventTypeAttrsMap = efadPool.getEventTypeAttrsMap();
			}
		}else{
			FilterAttrDataPool fadPool = (FilterAttrDataPool) object;
			if(null != fadPool){
				operationMap = fadPool.getOperationMap();
				attrDatasMap = fadPool.getAttrDatasMap();
				optionDatasMap = fadPool.getOptionDatasMap();
			}
		}
		updateFlag = (updateOperationMap(fileName,operationMap) && updateAttrDatasMap(fileName,attrDatasMap) && updateOptionDatasMap(fileName,optionDatasMap));

		updateFlag = (isEvent ? (updateEventTypeAttrsMap(fileName,eventTypeAttrsMap) && updateFlag) : updateFlag);
		return flag && updateFlag;
	}

	/**
	 * 更新时间分类属性列表到配置文件
	 * @param fileName
	 * @param eventTypeAttrsMap
	 * @return
	 */
	public static boolean updateEventTypeAttrsMap(String fileName,
			LinkedHashMap<String, List<AttrDataOption>> eventTypeAttrsMap) {

		boolean flag = false;
		if(!StringUtils.isEmpty(fileName)){
			String path = getPath() + File.separator + fileName +File.separator +"eventTypeAttrData.json";
			File file = new File(path);
			if(!file.exists()){//判断文件是否存在
	            try {
					file.createNewFile(); //创建文件
				} catch (IOException e) {
					logger.error("创建文件失败,path :"+path +"error message" +e.getMessage());
				}
	        }
			Json.toJsonFile(file, eventTypeAttrsMap, JsonFormat.nice());
			flag = true;
		}
		return flag;
	}

	/**
	 * 更新操作关系到配置文件
	 * @param fileName
	 * @param operationMap
	 * @return
	 */
	private static boolean updateOperationMap(String fileName,
			LinkedHashMap<String, Map<String, Operation>> operationMap) {

		boolean flag = false;

		if(!StringUtils.isEmpty(fileName)){
			String path = getPath() + File.separator + fileName +File.separator +"operation.json";
			File file = new File(path);
			if(!file.exists()){//判断文件是否存在
	            try {
					file.createNewFile(); //创建文件
				} catch (IOException e) {
					logger.error("创建文件失败,path :"+path +"error message" +e.getMessage());
				}
	        }
			Json.toJsonFile(file, operationMap, JsonFormat.nice());
			flag = true;
		}

		return flag;
	}

	/**
	 * 更新属性列表到配置文件
	 * @param fileName
	 * @param attrDatasMap
	 * @return
	 */
	public static boolean updateAttrDatasMap(String fileName, LinkedHashMap<String, AttrData> attrDatasMap) {

		boolean flag = false;
		if(!StringUtils.isEmpty(fileName)){
			String path = getPath() + File.separator + fileName +File.separator +"attrData.json";
			File file = new File(path);
			if(!file.exists()){//判断文件是否存在
	            try {
					file.createNewFile(); //创建文件
				} catch (IOException e) {
					logger.error("创建文件失败,path :"+path +"error message" +e.getMessage());
				}
	        }
			Json.toJsonFile(file, attrDatasMap, JsonFormat.nice());
			flag = true;
		}
		return flag;
	}

	/**
	 * 更新字典属性到配置文件中
	 * @param fileName
	 * @param optionDatasMap
	 * @return
	 */
	public static boolean updateOptionDatasMap(String fileName,
			LinkedHashMap<String, List<AttrDataOption>> optionDatasMap) {
		boolean flag = false;
		if(!StringUtils.isEmpty(fileName)){
			String path = getPath() + File.separator + fileName +File.separator +"attrDataOption.json";
			File file = new File(path);
			if(!file.exists()){//判断文件是否存在
	            try {
					file.createNewFile(); //创建文件
				} catch (IOException e) {
					logger.error("创建文件失败,path :"+path +"error message" +e.getMessage());
				}
	        }
			Json.toJsonFile(file, optionDatasMap, JsonFormat.nice());
			flag = true;
		}

		return flag;
	}


	/**
	 * 将模块下的所有配置文件读取到对象中
	 * @param tempFile
	 * @param filterMap
	 */
	private static void assemFilterAttrDataMap(File tempFile, Map<String, Object> filterMap) {

		EventFilterAttrDataPool efadPool = new EventFilterAttrDataPool();

		FilterAttrDataPool fadPool = new FilterAttrDataPool();

		String fileName = tempFile.getName();
		if("event".equals(fileName)){
			assemFilterAttrDataPools(fadPool,efadPool,filterMap,tempFile,fileName,true);

		}else{
			assemFilterAttrDataPools(fadPool,efadPool,filterMap,tempFile,fileName,false);
		}

	}




	/**
	 * 待优化
	 * 组装过滤条件属性
	 * @param fadPool
	 * @param efadPool
	 * @param filterMap
	 * @param tempFile
	 * @param fileName
	 * @param isEvent
	 */
	@SuppressWarnings("unchecked")
	private static void assemFilterAttrDataPools(FilterAttrDataPool fadPool, EventFilterAttrDataPool efadPool, Map<String, Object> filterMap,
                                                 File tempFile, String fileName, boolean isEvent) {
		File[] sonFileArray = tempFile.listFiles();
    	for(int j = 0 ; j < sonFileArray.length ; j++){
    		File tempSonFile = sonFileArray[j];
    		if(tempSonFile.isFile()){
    			String sonfileName = tempSonFile.getName();

				try {
					StringBuffer fileSbuffer = readFileToBuffer(tempSonFile,Charset.forName("UTF-8").toString());

	    			switch(sonfileName){
	    				case "attrData.json":
	    					LinkedHashMap<String,AttrData> attrDataMap = new LinkedHashMap<String,AttrData>();
	    					attrDataMap = (LinkedHashMap<String, AttrData>) Json.fromJson(NutType.map(String.class, AttrData.class), fileSbuffer);
	    					if(isEvent){
	    						efadPool.setAttrDatasMap(attrDataMap);
	    					}else{
	    						fadPool.setAttrDatasMap(attrDataMap);
	    					}
	    					break;
	    				case "attrDataOption.json":
	    					LinkedHashMap<String,List<AttrDataOption>> attrDataOptionMap = new LinkedHashMap<String,List<AttrDataOption>>();
	    					attrDataOptionMap = (LinkedHashMap<String, List<AttrDataOption>>) Json.fromJson(NutType.map(String.class, NutType.list(AttrDataOption.class)), fileSbuffer);
	    					if(isEvent){
	    						efadPool.setOptionDatasMap(attrDataOptionMap);
	    					}else{
	    						fadPool.setOptionDatasMap(attrDataOptionMap);
	    					}
	    					break;
	    				case "operation.json":
	    					LinkedHashMap<String,Map<String,Operation>> operationMap = new LinkedHashMap<String,Map<String,Operation>>();
	    					operationMap = (LinkedHashMap<String, Map<String, Operation>>) Json.fromJson(NutType.map(String.class, NutType.map(String.class, Operation.class)) , fileSbuffer);
	    					if(isEvent){
	    						efadPool.setOperationMap(operationMap);
	    					}else{
	    						fadPool.setOperationMap(operationMap);
	    					}
	    					break;
	    				case  "eventTypeAttrData.json" :
	    					LinkedHashMap<String,List<AttrDataOption>> eventTypeAttrDataMap = new LinkedHashMap<String,List<AttrDataOption>>();
	    					eventTypeAttrDataMap = (LinkedHashMap<String, List<AttrDataOption>>) Json.fromJson(NutType.map(String.class, NutType.list(AttrDataOption.class)), fileSbuffer);

	    					if(isEvent){
	    						efadPool.setEventTypeAttrsMap(eventTypeAttrDataMap);
	    					}
	    					break;
	    			}
				} catch (FileNotFoundException e) {
					logger.error("初始化过滤条件失败 ："+e.getMessage());
				}
    		}
    	}
    	if(isEvent){
    		filterMap.put(fileName, efadPool);
		}else{
			filterMap.put(fileName, fadPool);
		}


	}
	
	/**
	 * 将文件的内容读取到StringBuffer中去
	 * @param file
	 * 			文件
	 * @param charset
	 * 			文件字符编码
	 * @return
	 */
	public static StringBuffer readFileToBuffer(File file, String charset) throws FileNotFoundException{

		BufferedReader reader = null;
		FileInputStream fis = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis, charset));
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
			return sb;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb;
	}
}
