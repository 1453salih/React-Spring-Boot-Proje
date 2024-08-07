import defaultProfileImage from "@/assets/profile.png"
import {useAuthState} from "@/shared/state/context.jsx";
import {Button} from "@/shared/components/Button.jsx";
import {useState} from "react";
import {Input} from "@/shared/components/Input.jsx";
import {useTranslation} from "react-i18next";
import {updateUser} from "@/pages/User/components/ProfileCard/api.js";

export function ProfileCard({user}) {
    const authState = useAuthState();
    const [editMode, setEditMode] = useState(false);
    const { t } = useTranslation();

    const[newUsername, setNewUsername] = useState();
    const [apiProgress, setApiProgress] = useState(false);


    const onChangeUsername = (event)=>{
        setNewUsername(event.target.value);
    }

    const onClickSave=async ()=>{
        setApiProgress(true);
        try{
            await updateUser(user.id,{username:newUsername});
        }catch{

        }finally{
            setApiProgress(false);
        }
    }
    const isEditButtonVisible = true
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
                {!editMode && <span className="fs-3 d-block">{user.username}</span>}
                {isEditButtonVisible && <Button onClick={() => setEditMode(true)}>Edit</Button>}
                {editMode &&
                    <>
                        <Input
                            label={t("username")}
                            defaultValue={user.username}
                            onChange={onChangeUsername}
                        />
                        <Button apiProgress={apiProgress} onClick={onClickSave} >Save</Button>
                        <div className="d-inline m-1"></div>
                        <Button styleType="outline-secondary" onClick={()=>setEditMode(false)}>Cancel</Button>
                    </>
                }
            </div>
        </div>
    )
}