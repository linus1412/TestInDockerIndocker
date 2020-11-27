package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Transactional
public class WidgetService {

    private final WidgetRepository widgetRepository;

    public WidgetEntity findWidget(Long id) {
        return widgetRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("No such widget %s", id)));
    }

    public WidgetEntity saveWidget(WidgetEntity widgetEntity) {
        return widgetRepository.save(widgetEntity);
    }


}
