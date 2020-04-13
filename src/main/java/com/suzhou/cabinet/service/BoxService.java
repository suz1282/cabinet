package com.suzhou.cabinet.service;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suzhou.cabinet.entity.Box;
import com.suzhou.cabinet.enums.BoxSize;
import com.suzhou.cabinet.mapper.BoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Service
public class BoxService {
    @Autowired
    BoxMapper boxMapper;

    public void insertInit(String id) {
        List<Box> boxes=new ArrayList<>();
        for (int i = 0; i < BoxSize.BIG_BOX_NUMBER.getMessage(); i++) {
            Box b=new Box();
            b.setDelFlag("0");
            b.setIsEmpty("1");
            b.setBoxType("big");
            b.setD(BoxSize.BIG_BOX_DEPTH.getMessage());
            b.setH(BoxSize.BIG_BOX_HEIGHT.getMessage());
            b.setW(BoxSize.BIG_BOX_WIDTH.getMessage());
            b.setId(IdWorker.get32UUID());
            b.setCabinetId(id);
            boxes.add(b);
        }
        for (int i = 0; i < BoxSize.MIDDLE_BOX_NUMBER.getMessage(); i++) {
            Box b=new Box();
            b.setDelFlag("0");
            b.setIsEmpty("1");
            b.setBoxType("middle");
            b.setD(BoxSize.MIDDLE_BOX_DEPTH.getMessage());
            b.setH(BoxSize.MIDDLE_BOX_HEIGHT.getMessage());
            b.setW(BoxSize.MIDDLE_BOX_WIDTH.getMessage());
            b.setId(IdWorker.get32UUID());
            b.setCabinetId(id);
            boxes.add(b);
        }
        for (int i = 0; i < BoxSize.SMALL_BOX_NUMBER.getMessage(); i++) {
            Box b=new Box();
            b.setDelFlag("0");
            b.setIsEmpty("1");
            b.setBoxType("small");
            b.setD(BoxSize.SMALL_BOX_DEPTH.getMessage());
            b.setH(BoxSize.SMALL_BOX_HEIGHT.getMessage());
            b.setW(BoxSize.SMALL_BOX_WIDTH.getMessage());
            b.setId(IdWorker.get32UUID());
            b.setCabinetId(id);
            boxes.add(b);
        }
        boxMapper.insInit(boxes);
    }
}
