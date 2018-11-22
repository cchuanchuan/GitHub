package main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import stack.SeqStack;
import stack.Stack;

public class TableModels extends DefaultTableModel
{
	public TableModels(Stack stack)
	{
		String column[]= {"状态","车牌","到达时间"};
		this.addColumn("状态");
		this.addColumn("车牌");
		this.addColumn("到达时间");
		for(int i = 0;!stack.isEmpty();i++) {
			Car car = (Car) stack.pop();
			String carstring[] = {car.getCondition(),car.getNumber(),""+car.getTime()}; 
			System.out.println(car.getCondition()+","+car.getNumber()+","+car.getTime());
			this.addRow(carstring);
			
		}
		
	}
}
