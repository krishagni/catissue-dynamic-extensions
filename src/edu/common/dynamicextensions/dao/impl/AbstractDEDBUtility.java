package edu.common.dynamicextensions.dao.impl;

import edu.common.dynamicextensions.exception.DynamicExtensionsSystemException;


/**
 * @author ravi_kumar
 *
 */
public abstract class AbstractDEDBUtility implements IDEDBUtility
{
	/**
	 * This default implementation is used for 
	 * all database(mssql server,db2,postgresql) except mysql
	 * @param strDate date as string.
	 * @param removeTime true if time part has to remove
	 * @return formatted date.
	 */
	public String formatMonthAndYearDate(String strDate,boolean removeTime)
	{
		String month = strDate.substring(0, 2);
		String year = strDate.substring(3, strDate.length());
		return month + "-01-" + year + " 0:0";
	}
	
	/**
	 * This default implementation is used for
	 * all database(mssql server,db2,postgresql,oracle) except mysql
	 * @param strDate date as string.
	 * @param removeTime true if time part has to remove
	 * @return formatted date.
	 */
	public String formatYearDate(String strDate,boolean removeTime)
	{
		return "01-01-" + strDate + " 0:0";
	}
	/**
	 * For mssql server and db2 database this method is not defined,
	 * hence default implementation.
	 * @param ischecked  return 0 or 1 depending on boolean value passed.
	 * @return 0/1 or true/false for different databases.
	 */
	public String getValueForCheckBox(boolean ischecked)
	{
		return "";
	}
	
	/**
	 * method to clean database.
	 * @param args argument from main method.
	 * @throws DynamicExtensionsSystemException if database clean up fails.
	 */
	abstract public void cleanDatabase(String []args) throws DynamicExtensionsSystemException;
}
