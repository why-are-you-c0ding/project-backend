package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.shop.dataaccess.OptionGroupSpecificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionGroupSpecificationService {

    private final OptionGroupSpecificationRepository optionGroupSpecificationRepository;

    public List<Object> get(List<Long> optionGroupSpecificationIdList) {
        List<Object> result = new ArrayList<>();

        for (Long id : optionGroupSpecificationIdList) {
            result.add(optionGroupSpecificationRepository.findByIdAndStatus(id).get());
        }
        return result;
    }
}
