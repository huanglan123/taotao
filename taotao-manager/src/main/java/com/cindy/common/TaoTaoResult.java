package com.cindy.common;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaoTaoResult {
	private static final ObjectMapper MAPPER=new ObjectMapper();
	private Integer status;
	private String mag;
	private Object data;
	public static TaoTaoResult ok(){
		return new TaoTaoResult(null);
	}
	public static TaoTaoResult ok(Object data){
		return new TaoTaoResult(data);//接一个参数的构造方法
		
	}
	
	public static TaoTaoResult build(Integer status,String msg,Object data){
		return new TaoTaoResult(status,msg,data);//接多个参数的构造方法
		
	}
	public static TaoTaoResult build(Integer status, String msg){
		return new TaoTaoResult(status,msg,null);//接多个参数的构造方法
		
	}
	public TaoTaoResult(Integer status, String mag, Object data) {
		//有参构造
		this.status = status;
		this.mag = mag;
		this.data = data;
	}
	public TaoTaoResult() {
		super();//无参构造
	}
	
	public TaoTaoResult(Object data){
		this.status = 200;//一个参数的构造方法
		this.mag = "ok";
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMag() {
		return mag;
	}
	public void setMag(String mag) {
		this.mag = mag;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static TaoTaoResult formateToPojo(String jsonData,Class<?> clazz){
		try {
			if (clazz == null) {
                return MAPPER.readValue(jsonData, TaoTaoResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
		
	}
	 public static TaoTaoResult format(String json) {
	        try {
	            return MAPPER.readValue(json, TaoTaoResult.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * Object是集合转化
	     * 
	     * @param jsonData json数据
	     * @param clazz 集合中的类型
	     * @return
	     */
	    public static TaoTaoResult formatToList(String jsonData, Class<?> clazz) {
	        try {
	            JsonNode jsonNode = MAPPER.readTree(jsonData);
	            JsonNode data = jsonNode.get("data");
	            Object obj = null;
	            if (data.isArray() && data.size() > 0) {
	                obj = MAPPER.readValue(data.traverse(),
	                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
	            }
	            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	
}
