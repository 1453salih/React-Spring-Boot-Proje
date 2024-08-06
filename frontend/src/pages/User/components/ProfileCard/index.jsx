import defaultProfileImage from "@/assets/profile.png"
import {useAuthState} from "@/shared/state/context.jsx";
import {Button} from "@/shared/components/Button.jsx";
export function ProfileCard({user}){
    const authState = useAuthState();

    return (
        <div className="card">
            <div className="card-header text-center">
                <img
                    className="img-fluid rounded-circle shadow-sm"
                    src={defaultProfileImage}
                    alt="profile image"
                    width="200"
                />
            </div>
            <div className="card-body text-center">
                <span className="fs-3">{user.username}</span>
                {authState.id === user.id && <Button>Edit</Button>}
            </div>
        </div>
    )
}