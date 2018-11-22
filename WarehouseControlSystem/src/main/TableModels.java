package main;

import javax.swing.table.DefaultTableModel;

import bean.CargoBox;
import list.SinglyList;
public class TableModels extends DefaultTableModel
{
	public TableModels(Warehouse warehouse)
	{
		String column[]= {"编号","重量","时间","位置","货物名称","货物种类","客户编号","预计存放时间"};
		for(int i=0;i<column.length;i++) {
			this.addColumn(column[i]);
		}
		SinglyList<CargoBox> uplist = warehouse.getUplist();
		SinglyList<CargoBox> downlist = warehouse.getDownlist();
		for(int i = 0;i<warehouse.getM();i++) {
			if(i<warehouse.getN()) {
				CargoBox cargo = uplist.get(i);
				if(cargo!= null&&cargo.getBoxid()!=null) {
					String cargostring[] = {cargo.getBoxid(),cargo.getWeight(),cargo.getTime(),cargo.getPosition()
							,cargo.getCargoinfor().getName(),cargo.getCargoinfor().getCategroy()
							,cargo.getCargoinfor().getCustid(),cargo.getCargoinfor().getStoretime()}; 
					this.addRow(cargostring);
				}
			}else {
				CargoBox cargo = downlist.get(i-warehouse.getN());
				if(cargo!=null&&cargo.getBoxid()!=null) {
					String cargostring[] = {cargo.getBoxid(),cargo.getWeight(),cargo.getTime(),cargo.getPosition()
							,cargo.getCargoinfor().getName(),cargo.getCargoinfor().getCategroy()
							,cargo.getCargoinfor().getCustid(),cargo.getCargoinfor().getStoretime()}; 
					this.addRow(cargostring);
				}
			}
		}
		
	}
}
