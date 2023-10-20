package com.training.app.data.mapper

import com.training.app.data.entity.UserEntity
import com.training.app.domain.model.UserModel
import com.zappstudio.zappbase.data.mapper.IMapperJson

class UserMapper : IMapperJson<UserEntity, UserModel> {

    override fun getClassEntity(): Class<UserEntity> = UserEntity::class.java

    override fun toEntity(model: UserModel): UserEntity = with(model) {
        UserEntity(id, name, lastName, email, phoneNumber)
    }

    override fun toModel(entity: UserEntity): UserModel {
        return with(entity) {
            UserModel(
                id,
                name,
                lastName,
                email,
                phoneNumber
            )
        }
    }
}
