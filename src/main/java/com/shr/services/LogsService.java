package com.shr.services;

import com.shr.model.Logs;
import com.utils.Paramer;

/**
 * @description 后台日志操作管理接口
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/07/28
 * @version 1.0
 */
public interface LogsService {
	void addLog(Logs log);
	Paramer getLogs(Paramer p);
	Logs getLog(Logs log);
}
