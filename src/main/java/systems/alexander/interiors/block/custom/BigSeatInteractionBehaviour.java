package systems.alexander.interiors.block.custom;

import com.simibubi.create.content.contraptions.components.structureMovement.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.Contraption;
import com.simibubi.create.content.contraptions.components.actors.SeatInteractionBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

public class BigSeatInteractionBehaviour extends SeatInteractionBehaviour {

    @Override
    public void handleEntityCollision(Entity entity, BlockPos localPos, AbstractContraptionEntity contraptionEntity) {
        Contraption contraption = contraptionEntity.getContraption();
        int index = contraption.getSeats()
                .indexOf(localPos);
        if (index == -1)
            return;
        if (!ChairBlockExtendsSeat.canBePickedUp(entity))
            return;
        contraptionEntity.addSittingPassenger(entity, index);
    }
}
