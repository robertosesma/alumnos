package alumnos.model;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class TaskImport extends Task<Integer> {
	private final getAlumnosData d;
	private final String file;

    public TaskImport(getAlumnosData d, String file) {
        this.d = d;
        this.file = file;
    }
    
	@Override
	protected Integer call() throws Exception {
		int iter = 0;
        try {
            FileInputStream input = new FileInputStream(this.file);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            int total = sheet.getLastRowNum();
            for (iter = 1; iter < total; iter++) {
                this.d.importExcelRow(sheet.getRow(iter));
                
                updateProgress(iter,total);
            }
            wb.close();
            input.close();            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
        }
		return iter;
	}

}
