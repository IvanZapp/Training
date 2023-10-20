package com.training.app.data.mapper

import com.training.app.data.entity.SessionEntity
import com.training.app.domain.model.SessionModel
import com.zappstudio.zappbase.data.mapper.IMapper

class SessionMapper(
    private val userMapper: UserMapper
) : IMapper<SessionEntity, SessionModel> {
    override fun toEntity(model: SessionModel): SessionEntity {
        throw NotImplementedError()
    }

    override fun toModel(entity: SessionEntity): SessionModel {
        return with(entity) {
            SessionModel(
                token,
                refreshToken,
                userMapper.toModel(user)
            )
        }
    }
}
