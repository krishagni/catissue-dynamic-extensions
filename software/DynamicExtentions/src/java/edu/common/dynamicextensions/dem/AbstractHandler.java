
package edu.common.dynamicextensions.dem;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.common.dynamicextensions.entitymanager.AbstractBaseMetadataManager;
import edu.common.dynamicextensions.exception.DynamicExtensionsApplicationException;
import edu.common.dynamicextensions.exception.DynamicExtensionsSystemException;
import edu.common.dynamicextensions.ui.webui.util.WebUIManagerConstants;
import edu.wustl.common.audit.AuditManager;
import edu.wustl.dao.HibernateDAO;
import edu.wustl.dao.daofactory.DAOConfigFactory;
import edu.wustl.dao.exception.AuditException;
import edu.wustl.dao.exception.DAOException;

public class AbstractHandler extends HttpServlet implements WebUIManagerConstants
{

	protected Map<String, Object> paramaterObjectMap;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException
	{
		doPost(req, resp);
	}

	protected void initAuditManager()
	{
		try
		{
			AuditManager.init("DynamicExtensionsAuditMetadata.xml");
		}
		catch (AuditException e1)
		{
			System.out.println("Error initializing audit manager");
			e1.printStackTrace();
		}
	}

	protected void initializeParamaterObjectMap(HttpServletRequest req)
			throws DynamicExtensionsApplicationException
	{
		ObjectInputStream inputFromServlet = null;
		try
		{
			inputFromServlet = new ObjectInputStream(req.getInputStream());
			Object object = null;
			while ((object = inputFromServlet.readObject()) != null)
			{
				if (object instanceof Map)
				{
					paramaterObjectMap = (Map<String, Object>) object;
				}
			}
		}
		catch (ClassNotFoundException e)
		{
			throw new DynamicExtensionsApplicationException(
					"Error in reading objects from request", e);
		}
		catch (EOFException e)
		{
			System.out.println("End of file.");
		}
		catch (IOException e)
		{
			throw new DynamicExtensionsApplicationException(
					"Error in reading objects from request", e);
		}
		finally
		{
			try
			{
				inputFromServlet.close();
			}
			catch (IOException e)
			{
				throw new DynamicExtensionsApplicationException(
						"Error in reading objects from request", e);
			}
		}

	}

	protected void insertObject(Object object) throws DynamicExtensionsSystemException
	{
		HibernateDAO hibernateDAO = getHibernateDAO();
		try
		{
			hibernateDAO.insert(object);
			hibernateDAO.commit();
		}
		catch (DAOException e)
		{
			throw new DynamicExtensionsSystemException("Error occured while inserting object", e);
		}
		finally
		{
			try
			{
				hibernateDAO.closeSession();
			}
			catch (DAOException e)
			{
				throw new DynamicExtensionsSystemException(
						"Error occured while closing the DAO session", e);
			}
		}

	}

	protected HibernateDAO getHibernateDAO() throws DynamicExtensionsSystemException
	{
		HibernateDAO hibernateDao = null;
		try
		{
			hibernateDao = (HibernateDAO) DAOConfigFactory.getInstance().getDAOFactory("dem")
					.getDAO();
			hibernateDao.openSession(null);
		}
		catch (DAOException e)
		{
			throw new DynamicExtensionsSystemException(
					"Error occured while opening the DAO session", e);
		}
		return hibernateDao;
	}

	protected void writeObjectToResopnce(Object object, HttpServletResponse res)
			throws DynamicExtensionsApplicationException
	{
		ObjectOutputStream objectOutputStream = null;
		try
		{
			objectOutputStream = new ObjectOutputStream(res.getOutputStream());
			objectOutputStream.writeObject(AbstractBaseMetadataManager.getObjectId(object));
		}
		catch (IOException e)
		{
			throw new DynamicExtensionsApplicationException(
					"Error in writing object to the responce", e);
		}
		catch (NoSuchMethodException e)
		{
			throw new DynamicExtensionsApplicationException(
					"Error in writing object to the responce", e);
		}
		catch (IllegalAccessException e)
		{
		}
		catch (InvocationTargetException e)
		{
			throw new DynamicExtensionsApplicationException(
					"Error in writing object to the responce", e);
		}
		finally
		{
			try
			{
				objectOutputStream.flush();
				objectOutputStream.close();
			}
			catch (IOException e)
			{
				throw new DynamicExtensionsApplicationException(
						"Error in writing object to the responce", e);
			}

		}
	}
}
