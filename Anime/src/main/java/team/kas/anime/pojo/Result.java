package team.kas.anime.pojo;

import java.util.List;

/*
* 专门封装返回的数据（编写格式  接口文档   Layui的数据接口要求）
*
* {
  "code": 0,
  "msg": "",
  "count": 1000,
  "data": [{}, {}]
}
 *  */

public class Result {
    private Integer code = 0;
    private String msg = "success";
    private Integer count;
    private List data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
