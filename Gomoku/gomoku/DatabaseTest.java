package gomoku;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;

public class DatabaseTest
{
	String[]			users		= { "abcd", "ABC" };
	String[]			passwords	= { "123456", "123456" };
	private Database	db;
	private int			rando;

	@Before
	public void setUp() throws Exception
	{
		db = new Database();
		rando = ((int) Math.random() * users.length);
	}

	@Test
	public void testSetConnection() throws Exception
	{
		// 1. call setConnection() with db.properties
		db = new Database();
		// 2. call getConnection() and return a connect object (fis)
		Connection fis = db.getConnection();
		// 3. make sure connect object returned by getConnect is not null
		assertNotNull("Check setConnection", fis);

	}

	@Test
	public void testQuery() throws SQLException
	{
		// db.getConnection();
		String username = users[rando];
		String password = passwords[rando];
		String line = "select aes_decrypt(password, 'test')  from userdata where username =\'" + username + "\'";
		ArrayList<String> actual = db.query(line);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add(password);

		assertEquals("Check Query ", expected, actual);

	}

	@Test
	public void testExecuteDML() throws SQLException
	{
		String dml = "insert into users values('boa100', aes_encrypt('password', 'key'))";

		boolean actual = db.executeDML(dml);

		assertTrue("Check ExecuteDML", actual);
	}

}
