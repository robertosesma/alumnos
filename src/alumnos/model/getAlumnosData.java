package alumnos.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javafx.scene.control.Alert;

public class getAlumnosData {
    private static final String C_DRIVER = "jdbc:mariadb";
    private static Connection conn;
    
    public getAlumnosData(Boolean load) {
    	getAlumnosData.setConn(null);
    	
    	if (load) getConnection("rsesma","Amsesr.1977","localhost");
    }
    
    public Boolean getConnection(String user, String pswd, String server) {
        try {
            String url = C_DRIVER + "://" + server + ":3306/alumnos";
            Properties info = new Properties();
            info.setProperty("user", user);
            info.setProperty("password", pswd);
            getAlumnosData.setConn(DriverManager.getConnection(url, info));
            return true;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de registro");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(null);
            alert.showAndWait();
            return false;
        }
    }

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		getAlumnosData.conn = conn;
	}

    public ResultSet getRS(String fields, String table, String filter, String order) throws SQLException {
    	String sql = "SELECT " + fields + " FROM " + table;
    	if (filter.length()>0) sql = sql + " WHERE " + filter;
    	if (order.length()>0) sql = sql + " ORDER BY " + order;
    	return conn.prepareStatement(sql).executeQuery();
    }
    
    public void importExcelRow(org.apache.poi.ss.usermodel.Row row) {
        try {
            PreparedStatement q;
            q = conn.prepareStatement("INSERT INTO alumnos " +
            						  "(Periodo,Curso,DNI,Grupo,nombre,ape1,ape2,PC,Fijo," +
            						  "CLASE,Comentario,provincia,poblacion,trabajo,email) " +
                                      "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            q.setString(1,row.getCell(0).getStringCellValue());							// Periodo
            q.setString(2,row.getCell(1).getStringCellValue());							// Curso
            q.setString(3,row.getCell(2).getStringCellValue());							// DNI
            q.setString(4,row.getCell(3).getStringCellValue());							// Grupo
            q.setString(5,row.getCell(4).getStringCellValue());							// nombre
            q.setString(6,row.getCell(5).getStringCellValue());							// ape1
            q.setString(7,row.getCell(6).getStringCellValue());							// ape2
            q.setInt(8, (int) row.getCell(7).getNumericCellValue());					// PC
            q.setBoolean(9,row.getCell(8).getNumericCellValue()==1);					// Fijo
            q.setInt(10, (int) row.getCell(9).getNumericCellValue());					// CLASE
            q.setString(11,row.getCell(10).getStringCellValue());						// Comentario
            q.setString(12,row.getCell(11).getStringCellValue());						// provincia
            q.setString(13,row.getCell(12).getStringCellValue());						// poblacion
            q.setString(14,row.getCell(13).getStringCellValue());						// trabajo
            q.setString(15,row.getCell(14).getStringCellValue());						// email
            q.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void entregaPEC(EntPEC p) {
        try {
        	String sql = "INSERT INTO entregahonor (DNI, Curso, Periodo, npec, entregada, honor" +
        			(p.isMultiple() ? ", mdb, pdf" : "") +
        			") VALUES(?, ?, ?, ?, ?, ?" +
        			(p.isMultiple() ? ", ?, ?) " : ") ");
            PreparedStatement q;
            q = conn.prepareStatement(sql);
            q.setString(1, p.getDNI());
            q.setString(2, p.getCurso());
            q.setString(3, p.getPeriodo());
            q.setInt(4, p.getNPEC());
            q.setBoolean(5, true);
            q.setBoolean(6, p.getHonor());
            if (p.isMultiple()) {
            	// load extra fields: MDB & PDF
            	q.setBoolean(7, p.getMDB());
            	q.setBoolean(8, p.getPDF());
            }
            q.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void updateEntregaPEC(EntPEC p) {
        try {
        	String sql = "UPDATE entregahonor SET honor = ?, mdb = ?, pdf = ? " + 
        			" WHERE DNI = ? AND Curso = ? AND Periodo = ? AND npec = ?";
            PreparedStatement q;
            q = conn.prepareStatement(sql);
            q.setBoolean(1, p.getHonor());
            q.setBoolean(2, (p.isMultiple() ? p.getMDB() : null));
            q.setBoolean(3, (p.isMultiple() ? p.getPDF() : null));
            q.setString(4, p.getDNI());
            q.setString(5, p.getCurso());
            q.setString(6, p.getPeriodo());
            q.setInt(7, p.getNPEC());
            q.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void preguntaPEC(Pregunta p) {
        try {
        	String sql = "REPLACE INTO pec_estructura " + 
        			"(Periodo, Curso, pregunta, tipo, rescor, w, numopc, accion, datos, extra)" +
        			" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement q;
            q = conn.prepareStatement(sql);
            q.setString(1, p.getPeriodo());
            q.setString(2, p.getCurso());
            q.setString(3, p.getPregunta());
            q.setInt(4, p.getTipo());
            q.setString(5, p.getRescor());
            q.setFloat(6, p.getW());
            
            if (p.getNumopc()!=null && p.getNumopc()!=0) q.setInt(7, p.getNumopc());
            else q.setNull(7, java.sql.Types.INTEGER);
            
            if (p.getAccion()!=0) q.setInt(8, p.getAccion());
            else q.setNull(8, java.sql.Types.INTEGER);
            
            q.setString(9, p.getDatos());
            q.setString(10, p.getExtra());
            q.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

}
