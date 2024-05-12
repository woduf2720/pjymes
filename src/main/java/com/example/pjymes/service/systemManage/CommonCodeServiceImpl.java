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
    public Long register(CommonCodeDTO commonCodeDTO) {
        log.info("commonCode register...");
        Long parentId = commonCodeDTO.getParentId();
        CommonCode commonCode = modelMapper.map(commonCodeDTO, CommonCode.class);
        commonCode.setParent(commonCodeRepository.findById(parentId).orElseThrow());
        return commonCodeRepository.save(commonCode).getId();
    }

    @Override
    public CommonCodeDTO readOne(Long commonCodeId) {
        log.info("commonCode readOne...");
        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeId);
        CommonCode commonCode = result.orElseThrow();
        return modelMapper.map(commonCode, CommonCodeDTO.class);
    }

    @Override
    public void modify(CommonCodeDTO commonCodeDTO) {
        log.info("commonCode modify...");
        Optional<CommonCode> result = commonCodeRepository.findById(commonCodeDTO.getId());
        CommonCode commonCode = result.orElseThrow();
        commonCode.change(commonCodeDTO.getName(), commonCodeDTO.getDescription(), commonCodeDTO.getActive());
        commonCodeRepository.save(commonCode);
    }

    @Override
    public void remove(Long commonCodeId) {
        log.info("commonCode remove...");
        commonCodeRepository.deleteById(commonCodeId);
    }

    @Override
    public List<CommonCodeDTO> list() {
        log.info("commonCode list...");
        List<CommonCode> result = commonCodeRepository.findAll();
        return result.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommonCodeDTO> listByParentId(Long id) {
        log.info("listByParentId...");
        List<CommonCode> result = commonCodeRepository.findAllByParentId(id);
        return result.stream()
                .map(commonCode -> modelMapper.map(commonCode, CommonCodeDTO.class)).collect(Collectors.toList());
    }
}
