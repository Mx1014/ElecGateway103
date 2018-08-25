package com.heshun.dsm.handler.strategy;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.heshun.dsm.entity.Device;
import com.heshun.dsm.handler.strategy.bg5485.BG5485UnpStrategy;
import com.heshun.dsm.handler.strategy.def.DefaultUnpStrategy;
import com.heshun.dsm.handler.strategy.dtsd342._7n.DTSD3427NUnPackStrategy;
import com.heshun.dsm.handler.strategy.entech.disd687.DISD687UnpStrategy;
import com.heshun.dsm.handler.strategy.eqa300.abt.EQA300TUnpStrategy;
import com.heshun.dsm.handler.strategy.eqa300.abt.EQA300UnpStrategy;
import com.heshun.dsm.handler.strategy.eqa300.harmonic.EQA300HUnpStrategy;
import com.heshun.dsm.handler.strategy.h2o.H2oUnpStrategy;
import com.heshun.dsm.handler.strategy.logotype.LogoTypeUnpStrategy;
import com.heshun.dsm.handler.strategy.pd204.e.PD204EUnpStrategy;
import com.heshun.dsm.handler.strategy.pd204.z.PD204ZUnpStrategy;
import com.heshun.dsm.handler.strategy.pmc350.PMC350UnpStrategy;
import com.heshun.dsm.handler.strategy.sfo2.SFO2UnpStrategy;
import com.heshun.dsm.handler.strategy.switchmodule.SwitchModuleUnpackStrategy;
import com.heshun.dsm.handler.strategy.switchmodule.daishan.SwitchModuleUnpStrategy4Daishan;
import com.heshun.dsm.handler.strategy.switchmodule.hz.SwitchModuleUnpStrategy4HZ;
import com.heshun.dsm.handler.strategy.switchmodule.jingci.SwitchModuleUnpStrategy4JCS;
import com.heshun.dsm.handler.strategy.yht2tr.YHT2TRUnpStrategy;

/**
 * 构建一个对应类型的解包器
 * 
 * @author huangxz
 * 
 */
public class UnpackerFactory {
	/**
	 * 根据ip地址和与之通讯的cpu号，确定采集装置的型号，返回对应的解码器
	 */
	public static Abs103Unpacker<?, ?> fetchUnPacker(IoSession session, IoBuffer in, Device device) {
		if (device == null)
			return new DefaultUnpStrategy(session, in, device);
		switch (device.model.trim()) {
		case "logotype":
			return new LogoTypeUnpStrategy(session, in, device);
		case "eqa300":
			return new EQA300UnpStrategy(session, in, device);
		case "eqa300t":
			return new EQA300TUnpStrategy(session, in, device);
		case "DTSD342":
			return new DTSD3427NUnPackStrategy(session, in, device);
		case "eqa300h":
			return new EQA300HUnpStrategy(session, in, device);
		case "YHT2_TR":
			return new YHT2TRUnpStrategy(session, in, device);
		case "BG5485":
			return new BG5485UnpStrategy(session, in, device);
		case "SFO2":
			return new SFO2UnpStrategy(session, in, device);
		case "SwitchModule_HZ":
			return new SwitchModuleUnpStrategy4HZ(session, in, device);
		case "SwitchModule_DS":
			return new SwitchModuleUnpStrategy4Daishan(session, in, device);
		case "SwitchModule_JCS":
			return new SwitchModuleUnpStrategy4JCS(session, in, device);
		case "PD204E":
			return new PD204EUnpStrategy(session, in, device);
		case "PD204Z":
			return new PD204ZUnpStrategy(session, in, device);
		case "H2O":
			return new H2oUnpStrategy(session, in, device);
		case "Switch":
			return new SwitchModuleUnpackStrategy(session, in, device);
		case "DISD687":
			return new DISD687UnpStrategy(session, in, device);
		case "PMC350":
			return new PMC350UnpStrategy(session, in, device);
		default:
			return new DefaultUnpStrategy(session, in, device);
		}

	}

}
