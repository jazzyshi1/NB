package com.jz.snake.important.filter.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器表达式
 * 比如表达式: (((NOT b='value') AND (a='value')) or(n ='b'))的表达式如下:
 * 		new FilterExpression(OperatorLogic.AND)
 *					.addNotExp(new FunctionEQ(new EventFieldName("b"),right))
 *					.addExp(new FunctionEQ(new EventFieldName("a"),right));
 */
public class FilterExpression implements Serializable {
	private static final long serialVersionUID = 1657722838375612185L;

	public FilterExpression(OperatorLogic operator,List<FilterExpression> children){
		this.operator = operator;
		this.children = children;
	}
	
	public FilterExpression(IFunction function){
		this.operator = OperatorLogic.BASE;
		this.func = function;
	}

	/**
	 * 通过此构造函数构造表达式,然后再调用 addExp()来添加 Children 表达式
	 * @param operator
	 */
	public FilterExpression(OperatorLogic operator){
		this.operator = operator;
		this.children = new ArrayList<FilterExpression>();
	}
	
	/**
	 * 子表达式
	 */
	protected List<FilterExpression> children;
	
	/**
	 * 函数表达式,当它不为空时,operands 应为空，系统不再对 operands 进行处理
	 */
	protected IFunction func;
	
	/**
	 * 逻辑操作符 NOT/AND/OR
	 * {@link OperatorLogic}
	 */
	protected OperatorLogic operator;

	public List<FilterExpression> getChildren() {
		return children;
	}

	public void setChildren(List<FilterExpression> children) {
		this.children = children;
	}

	/**
	 * 将 Not表达式直接添加成为 children 的子结点
	 * @param func
	 * @return
	 */
	public FilterExpression addNotExp(IFunction func){
		if(this.children == null)
			this.children = new ArrayList<FilterExpression>();
		
		this.children.add(new FilterExpression(OperatorLogic.NOT).addExp(func));
		return this;
	}
	
	/**
	 * 将表达式直接添加成为 children 的子结点
	 * @param exp
	 * @return
	 */
	public FilterExpression addExp(FilterExpression exp) {
		if(this.children == null)
			this.children = new ArrayList<FilterExpression>();

		this.children.add(exp);
		return this;
	}

	/**
	 * 将函数做为子表达式添加成为 children 的子结点
	 * @param func
	 * @return
	 */
	public FilterExpression addExp(IFunction func) {
		if(this.children == null)
			this.children = new ArrayList<FilterExpression>();

		this.children.add(new FilterExpression(func));
		return this;
	}

    /**
     * 追加过滤条件到过滤条件
     * @param function
     */
	public void appendExpression(IFunction function){

		if(OperatorLogic.BASE.equals(this.operator)){
			IFunction ifunction = this.getFunc();
            if(null == ifunction){
                if(null != function){
                    this.func = function;
                }
            }else{
                this.operator = OperatorLogic.AND;
				this.setFunc(null);
                this.addExp(function);
                this.addExp(ifunction);
            }
		}else if(OperatorLogic.OR.equals(this.operator)){
			FilterExpression orFilterExpression = new FilterExpression(OperatorLogic.OR);
			orFilterExpression.setChildren(this.getChildren());
			List<FilterExpression> filterExpressions = new ArrayList<>();
			this.setChildren(filterExpressions);
			this.addExp(orFilterExpression);
            this.operator = OperatorLogic.AND;
            this.addExp(function);
		}else if(OperatorLogic.AND.equals(this.operator)){
			this.addExp(function);
		}


	}

	public IFunction getFunc() {
		return func;
	}

	public void setFunc(IFunction func) {
		this.func = func;
	}

	public OperatorLogic getOperator() {
		return operator;
	}

	public void setOperator(OperatorLogic operator) {
		this.operator = operator;
	}


	
}
