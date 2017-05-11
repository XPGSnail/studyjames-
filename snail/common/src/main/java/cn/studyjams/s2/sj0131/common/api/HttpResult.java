package cn.studyjams.s2.sj0131.common.api;

import java.io.Serializable;

/**
 * Created by panda.guo on 2016/11/26.
 */
public class HttpResult<T> implements Serializable{
    //统一模板
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
