package com.heshun.dsm.handler.strategy.switchmodule.hz;

import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.heshun.dsm.entity.Device;
import com.heshun.dsm.entity.ResultWrapper;
import com.heshun.dsm.entity.convert.AbsJsonConvert;
import com.heshun.dsm.entity.global.DataBuffer;
import com.heshun.dsm.handler.helper.IgnorePackageException;
import com.heshun.dsm.handler.helper.PacketInCorrectException;
import com.heshun.dsm.handler.strategy.AbsDeviceUnpackStrategy;

/**
 * 开关量模块
 * 
 * @author huangxz
 * 
 */
public class SwitchModuleUnpStrategy4HZ extends AbsDeviceUnpackStrategy<SwitchModule_HZConvert, SwitchModulePacket4HZ> {

	public SwitchModuleUnpStrategy4HZ(IoSession session, IoBuffer in, Device d) {
		super(session, in, d);
		dealChange = true;
	}

	@Override
	public String getDeviceType() {
		return "SwitchModule_HZ";
	}

	@Override
	public SwitchModule_HZConvert getConvert(SwitchModulePacket4HZ packet) {
		return new SwitchModule_HZConvert(packet);
	}

	@Override
	protected SwitchModulePacket4HZ handleTotalQuery(int size, Map<Integer, ResultWrapper> ycData,
			Map<Integer, ResultWrapper> yxData, Map<Integer, ResultWrapper> ymData) throws PacketInCorrectException {
		SwitchModulePacket4HZ packet = new SwitchModulePacket4HZ(mDevice.vCpu);
		packet.hasVisitor = yxData.get(1).getOriginData()[0] == 2 ? false : true;
		//
		packet.hasWater = yxData.get(2).getOriginData()[0] == 1 ? false : true;
		//
		packet.hasSmoke = yxData.get(3).getOriginData()[0] == 1 ? false : true;

		return packet;
	}

	@Override
	protected SwitchModulePacket4HZ handleChange(int size, Map<Integer, ResultWrapper> ycData,
			Map<Integer, ResultWrapper> yxData, Map<Integer, ResultWrapper> ymData) throws IgnorePackageException {
		AbsJsonConvert<?> c = null;

		if (DataBuffer.getInstance().getBuffer() == null
				|| DataBuffer.getInstance().getBuffer().get(getLogotype()) == null
				|| DataBuffer.getInstance().getBuffer().get(getLogotype()).get(mDevice.vCpu) == null) {
			c = new SwitchModule_HZConvert(new SwitchModulePacket4HZ(mDevice.vCpu));
		} else {
			c = DataBuffer.getInstance().getBuffer().get(getLogotype()).get(mDevice.vCpu);
		}

		SwitchModule_HZConvert orignal = (SwitchModule_HZConvert) c;
		SwitchModulePacket4HZ packet = orignal.getOriginal();

		packet.notify = true;
		try {
			packet.hasVisitor = yxData.get(1).getOriginData()[0] == 1 ? false : true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			packet.hasWater = yxData.get(2).getOriginData()[0] == 1 ? false : true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			packet.hasSmoke = yxData.get(3).getOriginData()[0] == 1 ? false : true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return packet;
	}
}
