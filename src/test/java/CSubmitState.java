import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "CSubmitState", propOrder = { "State", "MsgID", "MsgState", "Reserve" })  
public class CSubmitState {

	@XmlElement
	private int State;
	@XmlElement
	private int MsgID;
	@XmlElement
	private String MsgState;
	@XmlElement
	private int Reserve;
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	public int getMsgID() {
		return MsgID;
	}
	public void setMsgID(int msgID) {
		MsgID = msgID;
	}
	public String getMsgState() {
		return MsgState;
	}
	public void setMsgState(String msgState) {
		MsgState = msgState;
	}
	public int getReserve() {
		return Reserve;
	}
	public void setReserve(int reserve) {
		Reserve = reserve;
	}
	@Override
	public String toString() {
		return "ResponseBean [State=" + State + ", MsgID=" + MsgID
				+ ", MsgState=" + MsgState + ", Reserve=" + Reserve + "]";
	}
	
	
}
