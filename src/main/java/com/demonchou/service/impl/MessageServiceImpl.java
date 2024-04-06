package com.demonchou.service.impl;

import org.springframework.stereotype.Service;

import com.demonchou.service.MessageService;

/**
 *
 * @author hzzhouhongfei
 * @version $$ MessageServiceImpl, 2023/7/1 15:45 hzzhouhongfei $$
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService
{
	@Override
	public String getMessage()
	{
		return "hello world";
	}
}
