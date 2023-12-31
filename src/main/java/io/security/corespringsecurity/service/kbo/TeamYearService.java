package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.TeamYear;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeamYearService {

    List<TeamYear> findAll(String year);
    List<TeamYear> findAll();
}
