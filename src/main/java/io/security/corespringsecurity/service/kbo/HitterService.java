package io.security.corespringsecurity.service.kbo;

import io.security.corespringsecurity.domain.entity.kbo.Hitter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HitterService {

    List<Hitter> getList ();

    List<Hitter> getSortedHitterList(String columnName,String team);

    List<Hitter> findByTeam(String team);


}
