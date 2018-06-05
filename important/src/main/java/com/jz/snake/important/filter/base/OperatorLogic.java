package com.jz.snake.important.filter.base;

import java.io.Serializable;

/**
 * 逻辑操作符
 *
 */
public enum OperatorLogic implements Serializable {
	BASE,	//基本操作符,即没有逻辑操作
	NOT,
	AND,
	OR
}
