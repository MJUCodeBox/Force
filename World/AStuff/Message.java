package AStuff;

import java.io.Serializable;

import Function.AStuff.Function;
import WorldComponent.AStuff.WorldComponent;

public class Message implements Serializable{
	private static final long serialVersionUID = -6569825956010965283L;
	
	MessageType msgType;
	WorldComponent source;
	Function sourceFunction;
	Object[] arguments;
	
	public Message(MessageType msgType, WorldComponent source, Function sourceFunction, Object[] arguments) {
		this.msgType=msgType;
		this.source=source;
		this.sourceFunction=sourceFunction;
		this.arguments=arguments;
	}
	
	public void setMsgType(MessageType msgType) {this.msgType=msgType;}
	public MessageType getMsgType() {return msgType;}
	public WorldComponent getSource() {return source;}
	public Function getSourceFunction() {return sourceFunction;}
	public Object[] getArguments() {return arguments;}
}
