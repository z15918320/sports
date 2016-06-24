package com.sportsexp.common.util.dateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseJdbcDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public BaseJdbcDAO() {
		String driverClassName = Env.getInstance().getProperty("driver");
		String url = Env.getInstance().getProperty("url");
		String user = Env.getInstance().getProperty("user");
		String password = Env.getInstance().getProperty("password");
		Connection aConn = null;
		try {
			Class.forName(driverClassName);
			aConn = DriverManager.getConnection(url, user, password);
		} catch (Exception ce) {
			ce.printStackTrace();
		}
		this.conn = aConn;
	}

	public void closeAll() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ce) {
				ce.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ce) {
				ce.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ce) {
				ce.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ce) {
				ce.printStackTrace();
			}
		}

	}
}
