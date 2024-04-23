package com.example.pjymes.service.systemManage;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.dto.CommonCodeDTO;
import com.example.pjymes.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CommonCodeServiceImpl implements CommonCodeService{

    private final ModelMapper modelMapper;
    private final CommonCodeRepository commonCodeRepository;

    @Override
    public String register(CommonCodeDTO commonCodeDTO) {
        log.info("commonCode register...");
        String subCode = commonCodeDTO.getSubCode();
        if("00".equals(subCode)){
            Long count = commonCodeRepository.majorCodeCountSearch(commonCodeDTO);
            commonCodeDTO.setMajorCode(String.format("%02d", count));
        }else{
            Long count = commonCodeRepository.subCodeCountSearch(commonCodeDTO);
            commonCodeDTO.setSubCode(String.format("%02d", count));
        }
        commonCodeDTO.setCommonCodeId(commonCodeDTO.getMajorCode()+commonCodeDTO.getSubCode());
        CommonCode commonCode = modelMapper.map(commonCodeDTO, CommonCode.class);
        String commonCodeId = commonCodeRepository.save(commonCode).getCommonCodeId();
        return commonCodeId;
    }

    @Override
    public CommonCodeDTO readOne(String commonCodeId) {
        log.info("commonCode readOne...");
        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeId);
        CommonCode commonCode = result.orElseThrow();
        CommonCodeDTO commonCodeDTO = modelMapper.map(commonCode, CommonCodeDTO.class);
        return commonCodeDTO;
    }

    @Override
    public void modify(CommonCodeDTO commonCodeDTO) {
        log.info("commonCode modify...");
        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeDTO.getCommonCodeId());
        CommonCode commonCode = result.orElseThrow();
        commonCode.change(commonCodeDTO);
        commonCodeRepository.save(commonCode);
    }

    @Override
    public void remove(String commonCodeId) {
        log.info("commonCode remove...");
        commonCodeRepository.deleteById(commonCodeId);
    }

    @Override
    public List<CommonCodeDTO> list() {
        log.info("commonCode list...");
        List<CommonCode> result = commonCodeRepository.findAll();
        List<CommonCodeDTO> dtoList = result.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public List<CommonCodeDTO> majorCodeList() {
        log.info("majorCodelist...");
        List<CommonCode> result = commonCodeRepository.majorCodeSearch();
        List<CommonCodeDTO> dtoList = result.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public List<CommonCodeDTO> subCodeList(String majorCode) {
        log.info("subCodelist...");
        List<CommonCode> result = commonCodeRepository.subCodeSearch(majorCode);
        List<CommonCodeDTO> dtoList = result.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeDTO.class)).collect(Collectors.toList());
        return dtoList;
    }
}
